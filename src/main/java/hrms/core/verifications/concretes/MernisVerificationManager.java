package hrms.core.verifications.concretes;

import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.abstracts.MernisVerificationService;
import hrms.entities.concretes.JobSeeker;
import hrms.exceptions.MernisInvalidUserException;
import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

import java.util.Locale;

@Service
public class MernisVerificationManager implements MernisVerificationService {

    @Override
    public Result checkIfRealPerson(JobSeeker jobSeeker) {

        KPSPublicSoapProxy client = new KPSPublicSoapProxy();

        // boolean result;
        try {
            boolean result = client.TCKimlikNoDogrula(
                    Long.parseLong(jobSeeker.getNationalId()),
                    jobSeeker.getFirstName().toUpperCase(new Locale("tr")),
                    jobSeeker.getLastName().toUpperCase(new Locale("tr")),
                    Integer.parseInt(jobSeeker.getDateOfBirth()));
            if (result) {
                return new SuccessResult("User is valid.");
            }
        } catch (Exception e) {
            return new ErrorResult(e.getLocalizedMessage());
        }


        throw new MernisInvalidUserException("User information is not valid.");
    }

}
