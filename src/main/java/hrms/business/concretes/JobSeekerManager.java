package hrms.business.concretes;

import hrms.business.abstracts.JobSeekerService;
import hrms.core.utils.businessRulesCheck.BusinessRules;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.abstracts.MernisVerificationService;
import hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.dataAccess.abstracts.RoleDao;
import hrms.entities.concretes.JobSeeker;
import hrms.entities.concretes.Role;
import hrms.entities.dtos.request.JobSeekerRegisterDto;
import hrms.entities.dtos.response.JobSeekerResponseDto;
import hrms.exceptions.EmailAlreadyExistsException;
import hrms.exceptions.EntityNotFoundException;
import hrms.exceptions.NationalIdAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JobSeekerManager implements JobSeekerService {

    private final JobSeekerDao jobSeekerDao;
    private final RoleDao roleDao;
    private final MernisVerificationService mernisVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public JobSeekerManager(JobSeekerDao jobSeekerDao,
                            RoleDao roleDao,
                            MernisVerificationService mernisVerificationService,
                            PasswordEncoder passwordEncoder,
                            ModelMapper modelMapper) {
        this.jobSeekerDao = jobSeekerDao;
        this.roleDao = roleDao;
        this.mernisVerificationService = mernisVerificationService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result add(JobSeekerRegisterDto jobSeeker) {

        JobSeeker jobSeekerRequest = this.modelMapper.map(jobSeeker, JobSeeker.class);

        Result result = BusinessRules.Run(
                this.mernisVerificationService.checkIfRealPerson(jobSeekerRequest),
                this.checkIfNationalIdAlreadyExists(jobSeekerRequest),
                this.checkIfEmailAlreadyExists(jobSeekerRequest));

        if (result != null) {
            return result;
        }

        jobSeekerRequest.setRoles(addRoleToJobSeeker());
        jobSeekerRequest.setPassword(this.passwordEncoder.encode(jobSeeker.getPassword()));
        jobSeekerRequest = this.jobSeekerDao.save(jobSeekerRequest);
        JobSeekerResponseDto jobSeekerResponse = this.modelMapper.map(jobSeekerRequest, JobSeekerResponseDto.class);

        return new SuccessDataResult<>(jobSeekerResponse, "User information is valid. User was saved.");
    }

    @Override
    public Result delete(Integer id) {
        if (this.jobSeekerDao.existsById(id)) {
            this.jobSeekerDao.delete(this.jobSeekerDao.getById(id));
            return new SuccessResult("User deleted");
        }
        throw new EntityNotFoundException("User not found by id");
    }

    @Override
    public DataResult<List<JobSeekerResponseDto>> getAll() {

        List<JobSeekerResponseDto> jobSeekers = this.jobSeekerDao.findAll()
                .stream()
                .map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerResponseDto.class))
                .toList();

        return new SuccessDataResult<>(jobSeekers, "Candidates are listed.");
    }

    @Override
    public DataResult<JobSeekerResponseDto> getById(Integer id) {

        JobSeeker jobSeeker = this.jobSeekerDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found by Id"));
        JobSeekerResponseDto jobSeekerResponseDto = this.modelMapper.map(jobSeeker, JobSeekerResponseDto.class);

        return new SuccessDataResult<>(jobSeekerResponseDto, "Job Seeker found by id.");
    }

    private Result checkIfNationalIdAlreadyExists(JobSeeker jobSeeker) {

        var result = this.jobSeekerDao.existsJobSeekerByNationalId(jobSeeker.getNationalId());

        if (result) {
            throw new NationalIdAlreadyExistsException("National-ID already exists.");
        }
        return new SuccessResult();
    }

    private Result checkIfEmailAlreadyExists(JobSeeker jobSeeker) {
        var result = this.jobSeekerDao.existsJobSeekerByEmail(jobSeeker.getEmail());

        if (result) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        return new SuccessResult();
    }

    private Collection<Role> addRoleToJobSeeker() {

        return new ArrayList<>() {
            {
                add(roleDao.findByName("USER"));
                add(roleDao.findByName("CANDIDATE"));
            }
        };
    }

}
