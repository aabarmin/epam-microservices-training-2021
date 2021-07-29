package com.epam.training.miservices.services.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.epam.training.miservices.services.graphql.post.Post;
import com.epam.training.miservices.services.graphql.post.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
  @Autowired
  private PostRepository postRepository;

  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }
}
