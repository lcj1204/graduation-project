package graduation.graduationproject.project.dmain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties
@Getter
@Setter
public class Person {
    private Integer id; // 고유 아이디
    private Float area;
    private Float[] bbox;
    private final Integer category_id = 1;
    private Float[] keypoints;
    private Float score;
}
