import {defineStore} from 'pinia'
import {ref} from "vue";

const tableStore = defineStore('tableStore', () => {
    const map = ref(new Map())

    function set(tableObj) {
        if (tableObj && tableObj.tableId) {
            map.value.set(tableObj.tableId, tableObj)
        }
    }

    function remove(tableId) {
        if (tableId) {
            map.value.delete(tableId)
        }
    }

    function get(tableId) {
        return map.value.get(tableId)
    }

    function list() {
        return Array.from(map.value.values())
    }

    return {map, set, get, list}
})
const fieldStore = defineStore('fieldStore', () => {
    const map = ref(new Map())

    function set(fieldObj) {
        if (fieldObj && fieldObj.fieldId && fieldObj.tableId) {
            map.value.set(fieldObj.fieldId, fieldObj)
        }
    }

    function remove(fieldId) {
        if (fieldId) {
            map.value.delete(fieldId)
        }
    }

    function get(fieldId) {
        return map.value.get(fieldId)
    }

    function list(tableId) {
        return Array.from(map.value.values()).filter(t => t.tableId === tableId)
    }

    return {set, remove, get, list}
})
const onlineUserStore = defineStore('onlineUser', () => {
    const map = ref(new Map())

    function set(user) {
        if (user && user.userId) {
            map.value.set(user.userId, user)
        }
    }

    function remove(userId) {
        if (userId) {
            map.value.delete(userId)
        }
    }

    function list() {
        return Array.from(map.value.values())
    }

    return {set, remove, list}
})
const projectDataTypeStore = defineStore('projectDataTypeStore', () => {
    const map = ref(new Map())

    function clear() {
        map.value = new Map()
    }

    function set(dataType) {
        if (dataType && dataType.value) {
            map.value.set(dataType.value, dataType)
        }
    }

    function get(dataTypeValue) {
        if (dataTypeValue) {
            return map.value.get(dataTypeValue)
        }
    }

    function list() {
        return Array.from(map.value.values())
    }

    return {clear, set, get, map}
})

export default {tableStore, fieldStore, onlineUserStore, projectDataTypeStore}