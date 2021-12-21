package hrms.core.utils.businessRulesCheck;

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
