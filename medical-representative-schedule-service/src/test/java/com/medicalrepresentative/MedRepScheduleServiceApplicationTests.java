package com.medicalrepresentative;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicalrepresentativeschedule.MedRepScheduleServiceApplication;


@SpringBootTest(classes= {MedRepScheduleServiceApplication.class})
public class MedRepScheduleServiceApplicationTests {

	
	@Test
	void main() {
		MedRepScheduleServiceApplication.main(new String[] {});
	}

}
