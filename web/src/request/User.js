import request from "@/utils/request";

export default {
    login(form) {
        return request.post('token', form)
    }
}