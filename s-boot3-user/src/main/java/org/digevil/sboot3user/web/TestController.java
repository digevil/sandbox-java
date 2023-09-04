package org.digevil.sboot3user.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test Controller", description = "测试API")
@RestController
@RequestMapping("test")
public class TestController {



}
