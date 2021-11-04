package hrms.core.utils.Business;

import hrms.core.utils.results.Result;

public class BusinessRules {
    
    public static Result Run(Result ...results){
        for (Result result : results) {
            if (!result.isSuccess()) {
                return result;
            }
        }
        return null;
    }

}
