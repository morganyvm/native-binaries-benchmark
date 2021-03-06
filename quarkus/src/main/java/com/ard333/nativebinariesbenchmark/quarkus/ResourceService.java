package com.ard333.nativebinariesbenchmark.quarkus;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ResourceService {

	@Inject
	private ResourceRepository resourceRepository; 

	public Resource findById(Long id) {
		return resourceRepository.findById(id);
	}

	public List<Resource> findByPage(Integer page, Integer size) {
		return resourceRepository.findByPage(page - 1, size);
	}

	public List<Resource> findByResourceString(String resourceString, Integer page, Integer size) {
		return resourceRepository.findByResourceString(resourceString, page - 1, size);
	}

	@Transactional
	public void create(Resource resource) {
		resourceRepository.persist(resource);
	}

	@Transactional
	public void update(Long id, Resource resource) {
		Resource r = resourceRepository.findById(id);
		if (r != null) {
			r.setResourceString(resource.getResourceString());
			r.setResourceText(resource.getResourceText());
			resourceRepository.persist(r);
		}
	}

	@Transactional
	public void delete(Long id) {
		resourceRepository.deleteById(id);
	}

}