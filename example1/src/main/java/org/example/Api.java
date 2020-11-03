package org.example;

import lombok.AllArgsConstructor;
import org.example.model.TeamPageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Api {

  private CommonService commonService;

  @PostMapping("/gen")
  public void gen() {
    commonService.gen();
  }

  @GetMapping("/get")
  @ResponseBody
  public TeamPageDto get() {
    return commonService.get();
  }
}
