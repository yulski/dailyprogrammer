params = gets.chomp.split(" ").map &:to_i
numbers = gets.chomp.split(" ")[0..params[0]-1].map &:to_i
query_numbers = gets.split(" ")[0..params[1]-1].map &:to_i

for num in query_numbers
    x = 0
    i = 0
    loop do
        if numbers[i] > num
            x += 1
        else
            j = 0
            loop do
                if i != j and numbers[i] > (numbers[j]+1) and numbers[i] + numbers[j] > num
                    x += 1
                    break 
                end 
                j += 1
                break if j == numbers.size 
            end 
        end 
        i += 1
        break if i == numbers.size
    end 
    p x
end 

