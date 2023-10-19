import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

// export const useCounterStore = defineStore('counter', () => {
//   const count = ref(0)
//   const doubleCount = computed(() => count.value * 2)
//   function increment() {
//     count.value++
//   }
//
//   return { count, doubleCount, increment }
// })

export default {
  // 作废 改为 sessionStorage
  useUserStore : defineStore('user', {
    state: ()=> ({
      token: undefined,
      userInfo: {}
    }),
    getters:{
      isAuthenticated(state) {
        return state.token !== undefined && state.token !== null
      },
      getToken(state){
        return state.token
      }
    },
    actions:{
      regToken(token){
        this.token = token;
      },
      regUser(user){
        this.userInfo = user
      }
    }
  })


}