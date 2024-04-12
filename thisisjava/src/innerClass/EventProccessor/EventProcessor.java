package thisisjava.src.innerClass.EventProccessor;

public class EventProcessor {
    public void processEvent(String event){
        class EventHandler{
            public void handle(){
                System.out.println("처리 중인 이벤트: " + event);
            }
        }

        EventHandler handler = new EventHandler();
        handler.handle();
    }
}