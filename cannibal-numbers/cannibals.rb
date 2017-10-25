params = gets.chomp.split(" ").map &:to_i
numbers = gets.chomp.split(" ")[0..params[0]-1].map(&:to_i).sort.reverse
query_numbers = gets.split(" ")[0..params[1]-1].map &:to_i

for num in query_numbers
    x = 0
    i = 0
    eaten = []
    loop do
        break if eaten.include?(i)
        if numbers[i] >= num
            x += 1
        else
            j = numbers.size - 1
            curr_val = numbers[i]
            curr_eaten = []
            loop do
                if i != j and numbers[i] > numbers[j] and eaten.include?(j) == false and curr_eaten.include?(j) == false
                    curr_val += 1
                    curr_eaten.push j
                    if curr_val >= num
                        x += 1
                        curr_eaten.each { |e| eaten.push e }
                        break
                    end 
                end 
                j -= 1
                break if j < 0
            end 
        end 
        i += 1
        break if i == numbers.size
    end 
    p x
end 

