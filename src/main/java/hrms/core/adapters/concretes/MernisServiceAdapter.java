package hrms.core.adapters.concretes;

import java.rmi.RemoteException;

import hrms.core.adapters.abstracts.JobSeekerCheckService;
import hrms.core.utils.results.ErrorResult;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.entities.concretes.JobSeeker;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements JobSeekerCheckService {

    @Override
    public Result checkIfRealPerson(JobSeeker jobSeeker) throws NumberFormatException, RemoteException {

        KPSPublicSoapProxy client = new KPSPublicSoapProxy();

        boolean result = client.TCKimlikNoDogrula(Long.parseLong(jobSeeker.getNationalId()), jobSeeker.getFirstName().toUpperCase(), 
        		jobSeeker.getLastName().toUpperCase(), Integer.parseInt(jobSeeker.getDateOfBirth()));
        if (!result) {
            return new ErrorResult("User not valid");
        }

        return new SuccessResult("User valid.");
    }

}