package com.example.triton.tag.service;

import com.example.triton.tag.entity.Tag;
import com.example.triton.tag.entity.UserTag;
import com.example.triton.tag.repository.TagRepository;
import com.example.triton.tag.repository.UserTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagService {
    private TagRepository tagRepository;
    private UserTagRepository userTagRepository;

    public void addUserTags(Long uid, List<String> tagNames){
        for(String tagName : tagNames){
            Optional<Tag> name = tagRepository.findTagByTagName(tagName);
            if(name.isPresent()){
                Tag tag = name.get();
                UserTag userTag = new UserTag();
                userTag.setUid(uid);
                userTag.setTid(tag.getTid());
                userTagRepository.save(userTag);
            }
        }
    }

    public List<String> getUserTags(Long uid){
        List<Long> tidList = userTagRepository.findTagIdByUid(uid);
        List tagNames = new ArrayList<>();
        for (Long tid : tidList){
            Tag tag = tagRepository.findTagByTid(tid).get();
            tagNames.add(tag.getTagName());
        }
        return tagNames;
    }
}

/*TODO

senior -> 입력 : description ( tag 5 ) //


 */
