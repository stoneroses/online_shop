package com.wang.michael.online_shop.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wang.michael.online_shop.exception.LinkNotFound;
import com.wang.michael.online_shop.model.Link;
import com.wang.michael.online_shop.repository.LinkRepository;
import com.wang.michael.online_shop.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    @Transactional(rollbackFor = LinkNotFound.class)
    public Link delete(Long id) throws LinkNotFound {
        Link deletedLink = linkRepository.findOne(id);
        if (deletedLink == null) {
            throw new LinkNotFound();
        }
        linkRepository.delete(deletedLink);
        return deletedLink;
    }

    @Override
    @Transactional
    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    @Override
    @Transactional
    public Link findById(Long id) throws LinkNotFound {
        Link result = linkRepository.findOne(id);
        if (result == null) {
            throw new LinkNotFound();
        }
        return result;
    }

    @Override
    @Transactional
    public Link save(Link link) {
        if (link.getId() != null) {
            Link oldLink = linkRepository.findOne(link.getId());
            link.setCreatedDateTime(oldLink.getCreatedDateTime());
            link.setUpdatedDateTime(new DateTime());
        } else {
            link.setCreatedDateTime(new DateTime());
        }
        return linkRepository.save(link);
    }

}
