   // 1.Ip地址转换
    public class IpUtil {
        public static int ipToInt(String ip) {
            // 没有考虑错误输入，可以使用正则进行一次验证
            // ((25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))
            int index = 0;
            int result = 0;
            for (int i = 3; i >= 0; i--) {
                int num = 0;
                char t;
                while (index < ip.length() && (t = ip.charAt(index++)) >= '0' && t <= '9') {
                    num = num * 10 + t - '0';
                }
                result += num << (8 * i);
            }
            return result;
        }


        public static String ipToStr(int ip) {
            StringBuilder sb = new StringBuilder();
            for (int i = 3; i >= 0; i--) {
                sb.append((ip >>> (8 * i)) & 0xff).append('.');
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
    }
    
    
  // 2. 是否存在两数只和等于sum
    boolean find(int[] data, int sum) {

        if (data == null || data.length < 2) return false;
        int i = 0, j = data.length - 1;
        while (i < j) {
            int t = data[i] + data[j];
            if (t == sum) return true;
            if (t > sum) j--;
            else i++;
        }
        return false;
    }
    
    
    // 3. N个排序数组的TopK
    long[] topN(long[][] array, int k) {
        if (array == null || array.length == 0 || k <= 0) return new long[0];

        // 判断k的合法性，这里认为每一个数组的长度不相等
        int count = 0;
        for (long[] rows : array) {
            count += rows.length;
            if (count >= k) break;
        }
        if (k > count) k = count;

        long[] res = new long[k];
        final long[][] arr = array;
        PriorityQueue<int[]> heap =
                new PriorityQueue<>(k, (e1, e2) -> Long.compare(arr[e2[0]][e2[1]], arr[e1[0]][e1[1]]));
        for (int i = 0; i < arr.length; i++) {
            heap.add(new int[]{i, arr[i].length - 1});
        }
        int index = 0;
        while (index < k) {
            int[] e = heap.poll();
            res[index++] = arr[e[0]][e[1]];
            if (e[1] > 0) {
                heap.add(new int[]{e[0], e[1] - 1});
            }
        }
        return res;
    }
