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
        },
        
        connectCommunity: (state, payload) => {
            if(payload.member) {
                state.connectedCommunity = payload;
            }
        },
        
        connectMenu: (state, payload) => {
            if(payload.member) {
                state.connectedMenu = payload;
            }
        }

    }

})