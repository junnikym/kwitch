package tv.junnikym.kwitch.channel.dao;

import tv.junnikym.kwitch.channel.vo.ChannelVO;

public interface ChannelDAO {
	
	int regist(ChannelVO vo) throws Exception;
	
}