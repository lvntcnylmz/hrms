package hrms.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {

    private String githubLink;
    private String linkedinLink;
    private String instagramLink;
    private String twitterLink;
    private String facebookLink;

}
