import request from "@/utils/request";

export default {
    query(form) {
        return request.post('project/my-project', form)
    },
    delete(id) {
        return request.post('project/delete', [id])
    },
    save(form) {
        return request.post('project/save', form)
    }

}