set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "rest_small_output.png"
set title "Response Time Graph - REST (1 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "rest_small.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "rest_medium_output.png"
set title "Response Time Graph - REST (5 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "rest_medium.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "rest_large_output.png"
set title "Response Time Graph - REST (10 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "rest_large.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_unary_small_output.png"
set title "Response Time Graph - gRPC unary (1 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_unary_small.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_unary_medium_output.png"
set title "Response Time Graph - gRPC unary (5 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_unary_medium.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_unary_large_output.png"
set title "Response Time Graph - gRPC unary (10 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_unary_large.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_stream_small_output.png"
set title "Response Time Graph - gRPC stream (1 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_stream_small.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_stream_medium_output.png"
set title "Response Time Graph - gRPC stream (5 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_stream_medium.data" using 9 smooth sbezier with lines title ""



set terminal pngcairo enhanced font "consolas,15" fontscale 1.0 size 1600,900
set output "grpc_stream_large_output.png"
set title "Response Time Graph - gRPC stream (10 Req./time)"
set grid y
set grid x
set xlabel "No. of Requests"
set ylabel "Response Time (ms)"
plot "grpc_stream_large.data" using 9 smooth sbezier with lines title ""