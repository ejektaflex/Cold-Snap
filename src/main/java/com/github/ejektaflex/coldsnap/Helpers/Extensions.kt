import net.minecraftforge.fml.common.eventhandler.Event

fun Event.deny() {
    this.result = Event.Result.DENY
}
