# this solution was "inspired" by a nice and easy C# solution in the comments of the challenge 
ARGV.each do |num|
    num = num.to_i
    range = num * 2
    step = 1
    arr = Array 1..range

    j = 1
    loop do
        arr.delete_at j
        break if j >= arr.size
        j += 1
    end

    while step < arr.size
        i = arr[step] - 1
        loop do
            arr.delete_at i
            break if i >= arr.size
            i += arr[step] - 1
        end
        step += 1
    end

    if arr.include?(num)
        puts num.to_s + " is a lucky number"
    else
        after_index = arr.index { |x| x > num }
        before_index = after_index - 1
        puts arr[before_index].to_s + " < " + num.to_s + " < " + arr[after_index].to_s
    end
end
