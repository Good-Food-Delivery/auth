package goodfooddelivery.mapper;

import goodfooddelivery.dto.request.UserAuthCreateRequestDTO;
import goodfooddelivery.model.UserAuth;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T23:25:36+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
public class UserAuthMapperImpl implements UserAuthMapper {

    @Override
    public UserAuth toUser(UserAuthCreateRequestDTO user) {
        if ( user == null ) {
            return null;
        }

        UserAuth userAuth = new UserAuth();

        userAuth.setEmail( user.getEmail() );
        userAuth.setPassword( user.getPassword() );

        return userAuth;
    }
}
