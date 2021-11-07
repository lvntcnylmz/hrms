package hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobAdvertisementService;
import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao jobAdvertisementDao;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
        return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.save(jobAdvertisement), "Open position added.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Open positions have been listed.");
    }
    
}
