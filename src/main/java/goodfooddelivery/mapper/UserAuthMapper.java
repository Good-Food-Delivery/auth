package goodfooddelivery.mapper;
import goodfooddelivery.dto.request.UserAuthCreateRequestDTO;
import goodfooddelivery.model.UserAuth;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAuthMapper {
    UserAuthMapper INSTANCE = Mappers.getMapper(UserAuthMapper.class);

    UserAuth toUser(UserAuthCreateRequestDTO user);

}