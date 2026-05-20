import java.util.Scanner;

public class maxAreaOfIsland_695 {

    /*
    方法一：类似 leetcode200的方法一，只做了一些改动
     */
    static int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static int area = 0;  // 定义为全局变量，否则在(i,j)向四周遍历时传入的area不能实现不同方向的累加

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            int[][] nums = new int[row][col];

            for(int i=0; i<row;i++){
                for(int j=0; j<col; j++){
                    nums[i][j] = scanner.nextInt();
                }
            }


            // 处理
            boolean[][] visited = new boolean[row][col];

            int max = 0;   // 改动

            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){

                    if(nums[i][j]==1 && !visited[i][j]){

                        visited[i][j] = true;

                        area++;    // 改动

                        dfs(nums, visited, i,j);

                        max = Math.max(max, area);

                        area = 0;   // 清空！！！
                    }


                }
            }

            System.out.println(max);

        }

        scanner.close();
    }



    static void dfs(int[][] nums, boolean[][] visited, int x, int y){

        for(int i=0; i<4; i++){

            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];

            if(nextX<0 || nextX>=nums.length || nextY<0 || nextY>=nums[0].length){
                continue;
            }

            if(nums[nextX][nextY]==1 && !visited[nextX][nextY]){
                visited[nextX][nextY] = true;
                area++;    // 改动

                dfs(nums, visited, nextX, nextY);
            }


        }


    }



    /*
    方法二：深搜 from 我， 优化
     */
    int curArea = 0;    // 定义为全局变量，否则在(i,j)向四周遍历时传入的curMax不能实现不同方向的累加

    public int maxAreaOfIsland(int[][] grid) {   // 为0：不是岛屿   为1：是岛屿，且未访问   为2：是岛屿，且以访问

        int row = grid.length;
        int col = grid[0].length;

        int maxArea = 0;

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){

                if(grid[i][j]==1){
                    dfs(grid,i,j);

                    maxArea = Math.max(maxArea,curArea);

                    curArea = 0;   // 清空
                }
            }
        }

        return maxArea;
    }



    private void dfs(int[][] grid, int x, int y){

        if(grid[x][y]==0 || grid[x][y]==2){
            return;
        }


        if(grid[x][y]==1){
            curArea++;
            grid[x][y] = 2;  // 访问过
        }

        // 搜索四个方向，看有没有 未访问的陆地
        if(x-1 >= 0){
            dfs(grid,x-1,y);
        }

        if(x+1 <= grid.length-1){
            dfs(grid, x+1, y);
        }

        if(y-1 >= 0){
            dfs(grid, x, y-1);
        }

        if(y+1 <= grid[0].length-1){
            dfs(grid, x, y+1);
        }
    }











}
