package computermaster.developercommunity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class DeveloperCommunityApplication

fun main(args: Array<String>) {
	runApplication<DeveloperCommunityApplication>(*args)
}
