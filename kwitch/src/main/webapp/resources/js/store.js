const gStore = new Vuex.Store({
    state: {
        member: {
            memberId: null,
        }
    },

    mutations: {

        setMember: (state, payload) => {
            if(payload.member) {
                state.member = payload.member;
            }
        }

    }

})