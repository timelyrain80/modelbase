package base.model.design.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActDto<T> {
    String type;
    Long projectId;
    T obj;
    /**
     * 执行动作 1=保存，-1=删除 0=heart beat
     */
    int act; //

    public static <T> ActDto<T> del(Long projectId, T obj) {
        return new ActDto<>(obj.getClass().getSimpleName(), projectId, obj, -1);
    }

    public static <T> ActDto<T> save(Long projectId, T obj) {
        return new ActDto<>(obj.getClass().getSimpleName(), projectId, obj, 1);
    }
    public static <T> ActDto<T> heartBeat() {
        return new ActDto<>("heartBeat", null, null, 0);
    }

}
