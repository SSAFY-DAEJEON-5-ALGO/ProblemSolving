import java.io.*;
import java.util.*;

public class Main {
    static int pointerPos;
    static int inputPos;
    static int[] jumpPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int memorySize = Integer.parseInt(st.nextToken());
            int cmdSize = Integer.parseInt(st.nextToken());
            int inputSize = Integer.parseInt(st.nextToken());

            String commands = br.readLine();
            String inputs = br.readLine();

            int [] arr = new int[memorySize];

            answer.append(simulation(arr, commands, inputs)).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String simulation(int[] arr, String commands, String inputs) {
        int cmdSize = commands.length();

        pointerPos = 0;
        inputPos = 0;

        jumpPos = getJumpPos(commands);

        int cmdIdx = 0; // 특정 명령어를 읽은 위치
        int cnt = 0;
        
        while (cnt <= 50000000 && cmdIdx < cmdSize) {
            cnt++;

            cmdIdx = doStep(arr, commands, inputs, cmdIdx);
        }

        if (cmdIdx == cmdSize) {
            return "Terminates";
        } else {
            int maxCmdIdx = cmdIdx;
            int minCmdIdx = cmdIdx;

            while (cnt-- > 0) {
                cmdIdx = doStep(arr, commands, inputs, cmdIdx);

                maxCmdIdx = Math.max(maxCmdIdx, cmdIdx);
                minCmdIdx = Math.min(minCmdIdx, cmdIdx);
            }
            return "Loops " + (minCmdIdx-1) + " " + maxCmdIdx;
        }
    }
    
    public static int doStep(int[] arr, String commands, String inputs, int cmdIdx) {
        char c = commands.charAt(cmdIdx);

        if (c == '-') {
            if (arr[pointerPos] == 0) {
                arr[pointerPos] = 255;
            } else {
                arr[pointerPos] = arr[pointerPos] - 1;
            }
        } else if (c == '+') {
            if (arr[pointerPos] == 255) {
                arr[pointerPos] = 0;
            } else {
                arr[pointerPos] = arr[pointerPos] + 1;
            }
        } else if (c == '<') {
            if (pointerPos == 0) {
                pointerPos = arr.length - 1;
            } else {
                pointerPos = pointerPos - 1;
            }
        } else if (c == '>') {
            if (pointerPos == arr.length - 1) {
                pointerPos = 0;
            } else {
                pointerPos = pointerPos + 1;
            }
        } else if (c == '[') {
            if (arr[pointerPos] == 0) {
                cmdIdx = jumpPos[cmdIdx];
            }
        } else if (c == ']') {
            if (arr[pointerPos] != 0) {
                cmdIdx = jumpPos[cmdIdx];
            }
        } else if (c == '.') {
            // 출력 생략
        } else if (c == ',') {
            if (inputPos < inputs.length()) {
                arr[pointerPos] = inputs.charAt(inputPos);
                inputPos++;
            } else {
                arr[pointerPos] = 255;
            }
        }

        cmdIdx++;
        return cmdIdx;
    }

    public static int[] getJumpPos(String commands) {
        int cmdSize = commands.length();
        int[] result = new int[cmdSize]; // 서로 연결되어 있는 괄호의 위치
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < cmdSize; i++) {
            char c = commands.charAt(i);
            if (c == '[') {
                stack.push(i);
            } else if (c == ']') {
                int temp = stack.peek();
                result[i] = temp; 
                result[temp] = i;
                stack.pop();
            }
        }

        return result;
    }
}