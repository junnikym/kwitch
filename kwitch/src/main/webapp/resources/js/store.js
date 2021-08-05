const gStore = new Vuex.Store({
    state: {
        member: {
            memberId: null,
        },
        communityRole: {
        	
        },
        communityRoleFlags: {
        	CH_ROLE_READ 			: 1 << 0,
        	CH_ROLE_WRITE 			: 1 << 1,
        	CH_ROLE_UPDATE 			: 1 << 2,
        	CH_ROLE_DELETE 			: 1 << 3,
        	CH_ROLE_LEAVE			: 1 << 4,
        	CH_ROLE_LIKE 			: 1 << 5,
        	CH_ROLE_DELETE_OTHERS	: 1 << 6,
        	CH_ROLE_READ_PRIVATE	: 1 << 7,
        	CH_ROLE_BAN				: 1 << 8,
        	CH_ROLE_FREEZE 			: 1 << 9,
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
        },
        
        setCommunityRole: (state, payload) => {
        	state.communityRole = payload;
        }
    }

})