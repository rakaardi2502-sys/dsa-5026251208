package lw05.prelab;

import java.util.*;

public class Main {
    static int[][] graph;
    static int size;
    static String[] names;

    // Adds a two-way walkway (complete, do not change)
    static void addEdge(int from, int to, int minutes) {
        graph[from][to] = minutes;
        graph[to][from] = minutes;
    }

    static List<Integer> bfs(int start) {                                       //Fungsi BFS yang mengembalikan daftar urutan gedung yang dikunjungi, mulai dari start.
        List<Integer> order = new ArrayList<>();                                //Daftar hasil urutan kunjungan.
        Set<Integer> visited = new HashSet<>();                                 //Himpunan gedung yang sudah dikunjungi, supaya tidak dikunjungi dua kali.
        Queue<Integer> queue = new LinkedList<>();                              //Antrean (FIFO) untuk menyimpan gedung yang menunggu diproses.
        queue.add(start);
        visited.add(start);                                                     //Gedung awal dimasukkan ke antrean dan langsung ditandai dikunjungi.
        while (!queue.isEmpty()) {                                              //Ulangi selama antrean masih berisi.
            int current = queue.poll();                                         //Ambil dan hapus gedung paling depan antrean.
            order.add(current);                                                 //Catat gedung itu ke urutan hasil.
            for (int next = 0; next < size; next++) {                           //Periksa semua gedung (0 sampai size-1) sebagai kandidat tetangga.
                if (graph[current][next] > 0 && !visited.contains(next)) {      //Syarat: ada jalan dari current ke next (nilai > 0) dan next belum dikunjungi.
                    visited.add(next);
                    queue.add(next);                                            //Tandai dikunjungi, lalu masukkan ke belakang antrean. Ditandai sekarang (bukan saat diambil) agar tidak masuk antrean dua kali.
                }
            }
        }
        return order;                                                           //Setelah antrean kosong, kembalikan urutan kunjungan.
    }

    static Set<Integer> dfsVisited = new HashSet<>();                           // shared by every recursive call of dfs()
    static List<Integer> dfsOrder = new ArrayList<>();                          // shared by every recursive call of dfs()

    static void dfs(int current) {
        dfsVisited.add(current);                                                // mark current as visited
        dfsOrder.add(current);                                                  //Tandai dan catat gedung sekarang.
        for (int next = 0; next < size; next++) {
            if (graph[current][next] > 0 && !dfsVisited.contains(next)) {
                dfs(next);
            }                                                                   //Untuk tiap tetangga yang belum dikunjungi, langsung masuk lebih dalam dengan memanggil dfs(next). Setelah buntu, rekursi kembali dan loop melanjutkan ke next berikutnya.
        }
    }

    static void dijkstraAlg(int start, int dest) {                              //Mencari waktu terpendek dari start ke semua gedung, lalu mencetak rute ke dest.
        int[] dist = new int[size];
        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
        }                                                                       //Array jarak sementara. Semua diisi “tak hingga” (nilai int terbesar), kecuali gedung awal yang jaraknya 0.
        dist[start] = 0;
        boolean[] visited = new boolean[size];                                  //Penanda gedung yang jaraknya sudah final (awalnya semua false).
        int[] prev = new int[size];                                             //prev[i] menyimpan gedung sebelum i pada rute terpendek, untuk menyusun rute nanti.

        for (int i = 0; i < size; i++) {                                        //Ulangi sebanyak jumlah gedung, karena tiap putaran memfinalkan satu gedung.
            int current = findTheNextNode(visited, dist);                       //Pilih gedung belum final dengan jarak terkecil, lalu finalkan.
            visited[current] = true;                                            // mark current as visited
            for (int next = 0; next < size; next++) {                           //Untuk setiap gedung next, hitung jarak jika lewat current.
                int newDist = dist[current] + graph[current][next];
                if (graph[current][next] > 0 && newDist < dist[next]) {         // Jika ada jalan dan jarak baru lebih pendek dari yang tercatat, perbarui dist dan catat bahwa next dicapai dari current.
                    dist[next] = newDist;
                    prev[next] = current;                                       // remember that next is reached from current
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }
        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        route(dest, start, prev);
        System.out.println();
        System.out.println("Total time: " + dist[dest] + " min");
    }

    // Helper of dijkstraAlg(): finds the unvisited building with the smallest distance
    static int findTheNextNode(boolean[] visited, int[] dist) {
        int min = Integer.MAX_VALUE;
        int closest = -1;                                                       //min menyimpan jarak terkecil yang ditemukan sejauh ini, closest menyimpan indeks gedungnya (-1 berarti belum ada).
        for (int i = 0; i < size; i++) {
            if (visited[i] == false && min > dist[i]) {                         // i is unvisited and closer than min
                min = dist[i];
                closest = i;
            }
        }
        return closest;                                                         //Telusuri semua gedung. Jika belum final dan jaraknya lebih kecil dari min, jadikan kandidat terbaik. Di akhir, kembalikan indeks gedung terdekat.
    }

    // Helper of dijkstraAlg(): prints the route from start to current
    static void route(int current, int start, int[] prev) {
        if (current == start) {                                                 //Basis rekursi: jika sudah sampai gedung awal, cetak namanya saja.
            System.out.print(names[current]); 
    } else {                                                                    //Jika belum, mundur dulu ke gedung sebelumnya lewat prev. Setelah rekursi kembali, baru cetak -> nama. Itu sebabnya hasilnya berurutan dari awal ke tujuan.
            route(prev[current], start, prev);                                  // print the route up to the previous building
            System.out.print(" -> " + names[current]);
        }
    }

    // Prints the buildings as A -> B -> C (complete, do not change)
    static void printPath(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(names[list.get(i)]);
            if (i < list.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }                                                                           //Mencetak daftar indeks sebagai nama gedung yang dipisah ->. Panah hanya dicetak jika bukan elemen terakhir, supaya tidak ada panah menggantung. println() di akhir pindah baris.

    public static void main(String[] args) {
        Scanner sc = new Scanner (Main.class.getResourceAsStream("campus.txt"));

        while (sc.hasNext()){
            size = sc.nextInt();
            graph = new int[size][size];
            names = new String[size];
            for (int i = 0; i < size; i++){
                names[i] = sc.next();
            }

            int iteration = sc.nextInt();
            for (int i = 0; i < iteration; i++){
                int prevVer = sc.nextInt();
                int desVer = sc.nextInt();
                int minutes = sc.nextInt();

                addEdge(prevVer, desVer, minutes);
            }

            int originVer = sc.nextInt();
            int destinationVer = sc.nextInt();

            System.out.println("=== BFS from Rektorat ===");
            printPath(bfs(originVer));
            System.out.println("=== DFS from Rektorat ===");
            dfs(originVer);
            printPath(dfsOrder);
            dijkstraAlg(originVer, destinationVer);

        }
    }
}