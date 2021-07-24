package com.pod2.medicalrepresentative;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pod2.medicalrepresentativeschedule.MedRepScheduleServiceApplication;


@SpringBootTest(classes= {MedRepScheduleServiceApplication.class})
public class MedRepScheduleServiceApplicationTests {

	
	@Test
	void main() {
		MedRepScheduleServiceApplication.main(new String[] {});
	}

}
