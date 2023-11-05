import projectStore from '../stores/stores.js'

class Action {
    support(type) {
        throw new Error('基类方法不能调用')
    }

    save(data) {
        throw new Error('基类方法不能调用')
    }

    delete(data) {
        throw new Error('基类方法不能调用')
    }
}

// 数据表
class TableAction extends Action {
    support(type) {
        return type === 'Table'
    }

    save(data) {
        projectStore.tableStore().set(data.obj)
    }

    delete(data) {
        projectStore.tableStore().remove(data.obj.tableId)
    }
}
// 在线用户操作类
class OnlineUserAction extends Action {
    support(type) {
        return type === 'User'
    }

    save(data) {
        projectStore.onlineUserStore().set(data.obj)
    }

    delete(data) {
        projectStore.onlineUserStore().remove(data.obj.userId)
    }
}
const action = [new TableAction()]
function open(projectId) {
    const sse = new EventSource(`/api/project/event/${projectId}`)
    sse.onopen = (e) => {
        console.info('sse opened')
    }
    sse.onerror = (e) => {
        console.error(e)
    }
    sse.onmessage = (e) => {
        const data = JSON.parse(e.data)
        console.info('sse on message',data, 'actions', action)
        action.forEach(act => {
            if (act.support(data.type)) {
                if (data.act === 1) {
                    act.save(data)
                } else if (data.act === -1) {
                    act.delete(data)
                }
            }
        })
    }
    return sse
}

export default {open}