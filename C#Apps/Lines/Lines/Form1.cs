using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lines
{
    public partial class Form1 : Form
    {
        Pen myPen = new Pen(Color.Black);
        Brush myBrush = new SolidBrush(Color.Black);
        Graphics g = null;

        static int center_x, center_y;
        static int start_x, start_y;
        static int end_x, end_y;

        static int my_angle = 0;
        static int my_length = 0;
        static int my_increment = 0;
        static int my_nr_lines = 0;

        public Form1()
        {
            InitializeComponent();
            start_x = canvas.Width *3;
            start_y = canvas.Height*3;
        }

        private void canvas_Paint(object sender, PaintEventArgs e)
        {
            myPen.Width = 1;
            my_length = int.Parse(length.Text);
            g = canvas.CreateGraphics();
            for(int i=0;i<my_nr_lines;i++)
                drawLines();
        }

        private void drawLines()
        {
            Random randomGn = new Random();
            myBrush =new SolidBrush(Color.FromArgb(randomGn.Next(255), randomGn.Next(255), randomGn.Next(255), randomGn.Next(255)));
            my_angle += int.Parse(angle.Text);
            my_length += int.Parse(increment.Text);

            end_x = (int)(start_x + Math.Cos(my_angle * 0.017453292519) * my_length);
            end_y = (int)(start_y + Math.Sin(my_angle * 0.017453292519) * my_length);

            Point[] points =
            {
                new Point(start_x,start_y),
                new Point(end_x, end_y)
            };
            float dx = Math.Abs(end_x - start_x);
            float dy = Math.Abs(end_y - start_y);
            start_x = end_x;
            start_y = end_y;
            g.DrawLines(myPen, points);
            //g.FillEllipse(myBrush, start_x, start_y,dx, dy);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            my_length = int.Parse(length.Text);
            my_increment = int.Parse(increment.Text);
            my_nr_lines = int.Parse(nr_lines.Text);
            my_angle = int.Parse(angle.Text);

            start_x = canvas.Width / 2;
            start_y = canvas.Height / 2;

            canvas.Refresh();


        }

    }
}
