package com.example.sample

// springframework の annotation を import する
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class SampleResponse(
	val status: String,
	val message: String
)

@RestController
public class SampleController {
	@GetMapping("/")
	fun hello() = SampleResponse("ok", "Hello, World!")
}
