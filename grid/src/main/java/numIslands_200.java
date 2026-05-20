import java.util.*;

public class numIslands_200 {

    /*
    可以看leetcode题解（非官方+官方）
     */



    /*
    方法一 深度搜索 from 随想录
     */
    int[][] dir = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};   // 方向数组

    public int numIslands(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        // visited数组，表示元素是否访问过，默认为false
        boolean[][] visited = new boolean[row][col];

        int count = 0;   // 统计多少岛屿


        // 遍历图
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){

                if(grid[i][j]=='1' && visited[i][j]==false){  // 是陆地，且没有访问过

                    count++;
                    visited[i][j] = true;

                    dfs_1(grid, visited, i, j);    // 将与现在的(i,j)相连的所有陆地都标记为访问过
                }
            }
        }

        return count;
    }


    // 深搜写法一
    // 将(x,y)相连的所有陆地都标记为访问过
    private void dfs_1(char[][] grid, boolean[][] visited, int x, int y){

        for(int i=0; i<4; i++){   // 利用dir实现上下左右四个方向的搜索

            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            // 防止越界
            if(nextX<0 || nextX>=grid.length || nextY<0 || nextY>=grid[0].length){
                continue;
            }

            // 因为要将(x,y)相连的所有陆地都标记为访问过，所以找 没访问过的陆地
            if(grid[nextX][nextY]=='1' && visited[nextX][nextY]==false){
                visited[nextX][nextY] = true;

                dfs_1(grid,visited,nextX,nextY);  // 深搜要递归,继续将(nextX,nextY)相连的所有陆地都标记为访问过
            }

        }
    }



    // 深搜写法二，有错误
    // 将(x,y)相连的所有陆地都标记为访问过
    private void dfs_2(char[][] grid, boolean[][] visited, int x, int y){

        if(grid[x][y]=='0' || visited[x][y]==true){  // 如果是水or访问过的陆地，终止
            return;
        }

        visited[x][y] = true;

        for(int i=0; i<4; i++){   // 利用dir实现上下左右四个方向的搜索

            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            // 防止越界
            if(nextX<0 || nextX>=grid.length || nextY<0 || nextY>=grid[0].length){
                continue;
            }

            dfs_2(grid,visited,nextX,nextY); // 不管是水还是陆地，直接递归
        }
    }



    // 广搜一
    // 将(x,y)相连的所有陆地都标记为访问过
    public void bfs1(char[][] grid, boolean[][] visited, int x, int y) {
        Deque<int[]> queue = new LinkedList<>();

        queue.addLast(new int[]{x,y});
        visited[x][y] = true;


        while(!queue.isEmpty()){

            int[] cur = queue.removeFirst();   // 将cur上下左右为 未访问的、为陆地的节点 加入queue


            for(int i=0 ;i<4; i++){

                int nextX = cur[0] + dir[i][0];
                int nextY = cur[1] + dir[i][1];


                // 防止越界
                if(nextX<0 || nextX>=grid.length || nextY<0 || nextY>=grid[0].length){
                    continue;
                }


                // 未访问的、为陆地的节点 加入queue
                if(grid[nextX][nextY]=='1' && visited[nextX][nextY]==false){
                    visited[nextX][nextY] = true;
                    queue.addLast(new int[]{nextX,nextY});
                }
            }
        }
    }



    /*
    leetcode题解优化：将visit数组，改为 grid[i][j] =2， 即 grid[i][j] =1表示陆地（未访问）， grid[i][j] =2 表示陆地（已访问）

    下面是对广搜的优化：不使用visited数组
     */
    public void bfs2(char[][] grid, int x, int y) {
        Deque<int[]> queue = new LinkedList<>();

        queue.addLast(new int[]{x,y});
        grid[x][y]='2';   // 代替visited


        while(!queue.isEmpty()){

            int[] cur = queue.removeFirst();   // 将cur上下左右为 未访问的、为陆地的节点 加入queue


            for(int i=0 ;i<4; i++){

                int nextX = cur[0] + dir[i][0];
                int nextY = cur[1] + dir[i][1];


                // 防止越界
                if(nextX<0 || nextX>=grid.length || nextY<0 || nextY>=grid[0].length){
                    continue;
                }


                // 未访问的、为陆地的节点 加入queue
                if(grid[nextX][nextY]=='1'){
                    grid[nextX][nextY] = '2';
                    queue.addLast(new int[]{nextX,nextY});
                }
            }
        }
    }








}
