import request from "@/utils/request";

export default {
    save(obj) {
        return request.post('table', obj);
    },
    delete(projectId, idList) {
        return request.post('table/delete', {projectId: projectId, tableIdList: idList})
    },

}