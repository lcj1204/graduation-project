package graduation.graduationproject.project.dmain.image;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

/**
 * DB에 사진을 저장하는 것은 비효율
 * 실제 사진은 서버의 특정위치에 저장하도록 하고 DB에는 사진에 대한 정보만을 저장한다.
 * 다시 사진을 읽을 때에는 저장된 정보를 이용해 불러올 것이다.
 */
@Entity
//@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 고유 아이디

    @Column(name = "original_file_name")
    private String original_file_name;

    @Column(name = "stored_file_path")
    private String stored_file_path;

    @Column(name = "file_size")
    private long file_size;

    @Column(name = "file_content_type")
    private String file_content_type;

    @CreationTimestamp
    private Date uploadDate;
//    private String name;
//    private byte[] data;
//    private String created;
}