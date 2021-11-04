package hrms.core.verifications;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.entities.concretes.JobSeeker;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisVerification implements MernisVerificationService {

    @Override
    public Result checkIfRealPerson(JobSeeker jobSeeker) throws NumberFormatException, RemoteException {

        KPSPublicSoapProxy client = new KPSPublicSoapProxy();

        boolean result = client.TCKimlikNoDogrula(Long.parseLong(String.valueOf(jobSeeker.getNationalId())),
                jobSeeker.getFirstName().toUpperCase(), jobSeeker.getLastName().toUpperCase(),
                Integer.parseInt(jobSeeker.getDateOfBirth()));

        if (result) {
            return new SuccessResult("User is valid.");
        }
        return new ErrorResult("User information is not valid. ");
    }

}
