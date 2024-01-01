package mvc.promiseme.common.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat
public enum ErrorCode implements EnumModel{

    //예시
    //회원 로그인 시 발생 가능 예외
    INVALID_User_Login(401, "U001", "존재하지 않는 고객 정보입니다."),
    INVALID_User_Password(401, "U002", "비밀번호가 일치하지 않습니다."),

    //중복여부 체크
    DUPLICATE_USER(400,"D001","중복된 이메일입니다."),

    //조회시 발생 가능 예외
    RPOJECT_NOT_FOUND(401,"P001","프로젝트를 찾을 수 없습니다."),
    CATEGORY_SERVER_ERROR(500,"P002","카테고리 불러오는 것을 실패하였습니다."),
    MEMBER_NOT_FOUND(401,"M001","해당하는 멤버를 찾을 수 없습니다."),
    CALENDER_NOT_FOUND(401,"C001","해당하는 일정을 찾을 수 없습니다.");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;

    }
    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}