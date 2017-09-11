=begin
    TODO: Optimize this - this solution works but is horrible. VERY slow.
    Input 100,000 takes almost 2 minutes, where it should take a few seconds.
    There is a C solution where 100,000 takes 1.7 secs, another C solution that
    takes 0.3 secs, a Python one that takes around 8 secs and another Python one
    that takes 0.02 seconds for 100,000.
=end

def lucky_nums_to(n)
    arr = Array 1..n
    index = 0
    inc = 2
    lim = arr.size
    loop do
        i = inc - 1
        to_d = []
        while i < lim
            to_d.push i
            i += inc
        end
        c = []
        j = 0
        while j < arr.size
            c.push arr[j] unless to_d.include?(j)
            j += 1
        end
        arr = c
        index += 1
        inc = arr[index]
        lim = arr.size
        break if inc >= lim
    end
    arr
end

def lucky_nums_from(n)
    to_nums = lucky_nums_to n
    before = to_nums.last
    after = nil
    x = 2
    nums = []
    loop do
        nums = lucky_nums_to(n * x)
        x += 1
        if nums.size > to_nums.size
            after = nums.first
            break
        end
    end
    from_nums = []
    nums.each do |i|
        from_nums.push i if i > n
    end
    from_nums
end

input = [103, 225, 997]
input.each do |i|
    to = lucky_nums_to i
    from = lucky_nums_from i
    if to.include? i
        puts "#{i} is a lucky number"
        exit
    end
    prev = to.last
    nxt = from.first
    puts "#{prev} < #{i} < #{nxt}"
end
