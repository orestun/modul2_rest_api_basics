package com.epam.esm.tag;

import com.epam.esm.exceptionHandler.DataValidationHandler;
import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.*;
import java.util.List;

/**
 * @author orest uzhytchak
 * */
@Service
@EnableTransactionManagement
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    /**
     * A service method for creating a new tag
     * in db, by calling a method of repository layer
     * @param tag the Tag object that will be added in db,
     *            in case of passing validation
     * @see TagRepository#createTag(Tag)
     * */
    @Transactional
    public boolean createTag(@Valid Tag tag){
        DataValidationHandler<Tag> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(tag);
        if(!errors.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format(errors)
            );
        }
        return tagRepository.createTag(tag);
    }

    /**
     * A service method for getting all tags
     * that are stored in db, by calling a method of repository layer
     * @see TagRepository#getTag()
     * */
    public List<Tag> getTag(){
        return tagRepository.getTag();
    }

    /**
     * A service delete method for deleting a tag
     * from db by id and by calling a method of repository layer
     * @param id the id of object that are going to be deleted
     * @see TagRepository#deleteTag(long)
     * */
    public boolean deleteTag(long id){
        if(tagRepository.isNotTagBySuchId(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("There is not such gift certificate with (id=%d)",id)
            );
        }
        return tagRepository.deleteTag(id);
    }

}
