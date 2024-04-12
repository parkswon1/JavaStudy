package thisisjava.src.innerClass.Network;

public class Main {
    public static void main(String[] args){
        Network.Message msg1 = new Network.Message("안녕하세요!");
        msg1.send();
        Network.Message msg2 = new Network.Message("반갑습니다!");
        msg2.send();

        System.out.println("네트워크를 통해 전송된 전체 메시지 수:" + Network.getTotalMessages());
    }
}
