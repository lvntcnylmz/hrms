package hrms.core.adapters;

import java.rmi.RemoteException;

import hrms.core.abstracts.JobSeekerCheckService;
import hrms.entities.concretes.JobSeeker;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements JobSeekerCheckService {

    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {

        KPSPublicSoapProxy client = new KPSPublicSoapProxy();

        boolean result = true;

        try {
            result = client.TCKimlikNoDogrula(Long.parseLong(jobSeeker.getNationalId()),
                    jobSeeker.getFirstName().toUpperCase(), jobSeeker.getLastName().toUpperCase(),
                    Integer.parseInt(jobSeeker.getDateOfBirth()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return result;
    }

}