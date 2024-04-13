package gukbi.bookplybackend.common.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController implements ErrorController {
  // vue에서 history모드 사용으로 url직접 접근 불가.
  // url 직접 접근할 경우 대체 경로 추가
  private final String ERROR_PATH = "/error";

  @GetMapping(ERROR_PATH)
  public String redirectRoot(){
    return "forward:/index.html";
  }

  public String getErrorPath(){
    return null;
  }
}
