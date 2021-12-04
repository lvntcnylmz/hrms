package hrms.core.verifications.concretes;

import hrms.exceptions.MernisInvalidUserException;
import org.springframework.stereotype.Service;

import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.abstracts.MernisVerificationService;
import hrms.entities.concretes.JobSeeker;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoap;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisVerificationManager implements MernisVerificationService {

    @Override
    public Result checkIfRealPerson(JobSeeker jobSeeker) {

        KPSPublicSoap client = new KPSPublicSoapProxy();

        boolean result;
        try {
            result = client.TCKimlikNoDogrula(Long.parseLong(String.valueOf(jobSeeker.getNationalId())),
                    jobSeeker.getFirstName().toUpperCase(), jobSeeker.getLastName().toUpperCase(),
                    Integer.parseInt(jobSeeker.getDateOfBirth()));
        } catch (Exception e) {
            return new ErrorResult(e.getLocalizedMessage());
        }

        if (result) {
            return new SuccessResult("User is valid.");
        }
        throw new MernisInvalidUserException("User information is not valid.");
    }

}
