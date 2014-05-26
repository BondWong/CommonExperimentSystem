package unitTest;

import static org.junit.Assert.*;
import model.Experiment;
import model.User;
import model.UserType;

import org.junit.Test;

public class SubmitReport {
	@Test
	public void testSubmitReport() {
		User student = new User();
		student.setUserType(UserType.STUDENT);
		Experiment experiment = new Experiment();
		student.submitReport(experiment, "hehe");
		
		assertEquals(1, experiment.getReportLinks().size());
	}
}
