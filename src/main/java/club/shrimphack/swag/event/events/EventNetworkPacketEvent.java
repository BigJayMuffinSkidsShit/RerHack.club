package club.shrimphack.swag.event.events;

import club.shrimphack.swag.event.EventStage;
import net.minecraft.network.Packet;

public class EventNetworkPacketEvent extends EventStage {
    public Packet m_Packet;

    public EventNetworkPacketEvent(Packet p_Packet)
    {
        super();
        m_Packet = p_Packet;
    }

    public Packet GetPacket()
    {
        return m_Packet;
    }

    public Packet getPacket()
    {
        return m_Packet;
    }
}
