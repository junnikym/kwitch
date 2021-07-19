package tv.junnikym.kwitch.channel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

	@Resource(name="ChannelDAO")
	ChannelDAO channelDAO;
	
	@Override
	public int regist(ChannelVO vo) throws Exception {
		int result = channelDAO;
		return 0;
	}

}
