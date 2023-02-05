package com.epam.esm.tag;

import com.epam.esm.exceptionHandler.DataValidationHandler;
import com.epam.esm.exceptionHandler.ItemNotFoundException;
import com.epam.esm.exceptionHandler.ServerException;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public boolean createTag(@Valid Tag tag){
        DataValidationHandler<Tag> dataValidationHandler = new DataValidationHandler<>();
        String errors = dataValidationHandler.errorsRepresentation(tag);
        if(!errors.isEmpty()){
            throw new ServerException(errors);
        }
        return tagRepository.createTag(tag);
    }

    public List<Tag> readTag(){
        return tagRepository.readTag();
    }

    public boolean deleteTag(long id){
        if(tagRepository.isNotTagBySuchId(id)){
            throw new ItemNotFoundException(String.format("There is not such tag with (id = %d)",id));
        }
        return tagRepository.deleteTag(id);
    }

}
