package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;

public class TeacherStudentCourse {
	private long teacherStudentCourseId;
	private Course course;
	private Teacher teacher;
	private Student student;
	
	private long courseLevel;
	private long bookletLevel;
	
	private Set<Result> results;
	private Set<TeacherFeedback> teacherFeedbacks;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	public TeacherStudentCourse(){}

	public TeacherStudentCourse(Course course, Teacher teacher,
			Student student, long courseLevel, long bookletLevel) {
		super();
		this.course = course;
		this.teacher = teacher;
		this.student = student;
		this.courseLevel = courseLevel;
		this.bookletLevel = bookletLevel;
	}

	/**
	 * @return the teacherStudentCourseId
	 */
	public long getTeacherStudentCourseId() {
		return teacherStudentCourseId;
	}

	/**
	 * @param teacherStudentCourseId the teacherStudentCourseId to set
	 */
	public void setTeacherStudentCourseId(long teacherStudentCourseId) {
		this.teacherStudentCourseId = teacherStudentCourseId;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the courseLevel
	 */
	public long getCourseLevel() {
		return courseLevel;
	}

	/**
	 * @param courseLevel the courseLevel to set
	 */
	public void setCourseLevel(long courseLevel) {
		this.courseLevel = courseLevel;
	}

	/**
	 * @return the bookletLevel
	 */
	public long getBookletLevel() {
		return bookletLevel;
	}

	/**
	 * @param bookletLevel the bookletLevel to set
	 */
	public void setBookletLevel(long bookletLevel) {
		this.bookletLevel = bookletLevel;
	}

	/**
	 * @return the results
	 */
	public Set<Result> getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Set<Result> results) {
		this.results = results;
	}

	/**
	 * @return the teacherFeedbacks
	 */
	public Set<TeacherFeedback> getTeacherFeedbacks() {
		return teacherFeedbacks;
	}

	/**
	 * @param teacherFeedbacks the teacherFeedbacks to set
	 */
	public void setTeacherFeedbacks(Set<TeacherFeedback> teacherFeedbacks) {
		this.teacherFeedbacks = teacherFeedbacks;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus the objStatus to set
	 */
	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public JSONObject toJsonSimple() {
		JSONObject returnJson = new JSONObject();
		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		return returnJson;
	}
	
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.COURSE, this.course.toJson());
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.STUDENT, this.student.toJson());
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonStudent() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.STUDENT, this.student.toJson());
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonTeacher() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.TEACHER, this.teacher.toJson());
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
	public JSONObject toJsonCourse() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.COURSE, this.course.toJson());
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}

	public JSONObject toJsonShowStudentAndTeacher() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TEACHERSTUDENTCOURSEID, this.teacherStudentCourseId);
		returnJson.put(Key.COURSE, this.course.toJson());
		returnJson.put(Key.TEACHER, this.teacher.toJsonSimple());
		returnJson.put(Key.STUDENT, this.student.toJsonSimple());
		returnJson.put(Key.COURSELEVEL, this.courseLevel);
		returnJson.put(Key.BOOKLETLEVEL, this.bookletLevel);

		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		return returnJson;
	}
	
}
